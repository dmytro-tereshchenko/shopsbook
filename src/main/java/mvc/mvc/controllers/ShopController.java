package mvc.mvc.controllers;

import mvc.mvc.bean.Category;
import mvc.mvc.bean.Shop;
import mvc.mvc.models.ShopModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@ControllerAdvice
public class ShopController {

    @Autowired
    ShopModel shopModel;

    @RequestMapping(value = "/index")
    public ModelAndView index(Model model) {
        return getAllShops(model);
    }

    @RequestMapping(value = "/create-shop", method=RequestMethod.GET)
    public ModelAndView createHumanGet() {
        List<Category> categories = shopModel.getAllCategories();
        return new ModelAndView("/shop/create", "categories", categories);
    }

    @RequestMapping(value = "/create-shop", method=RequestMethod.POST)
    public ModelAndView createHumanPost(@ModelAttribute("shop") Shop shop,
                                    BindingResult result, Model model) {
        boolean resultAdd = shopModel.createShop(shop);
        return getAllShops(model);
    }

    @RequestMapping(value = "/get-shop/{id}", method=RequestMethod.GET)
    public ModelAndView getHuman(@PathVariable(value="id") int id, Model theModel) {
        Shop shop =  shopModel.getShop(id);
        theModel.addAttribute("categories", shopModel.getAllCategories());
        return new ModelAndView("/shop/edite", "shop", shop);
    }

    @RequestMapping(value = "/delete-shop/{id}", method=RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable(value="id") int id, Model model) {
        boolean result = shopModel.deleteShop(id);
        return getAllShops(model);
    }

    @RequestMapping(value = "/update-shop", method=RequestMethod.POST)
    public ModelAndView updateHuman(@ModelAttribute("shop") Shop shop,
                                    BindingResult result, Model model) {
        boolean resultUpdate = shopModel.updateShop(shop);
        return getAllShops(model);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getAllShops(Model model) {
        List<Shop> people =  shopModel.getAllShops();
        model.addAttribute("categories", shopModel.getAllCategories());
        return new ModelAndView("/index", "resultObject", people);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam String shopName, @RequestParam int categoryId,
                               @RequestParam String address, Model model) {
        List<Shop> people =  shopModel.searchShops(shopName, categoryId, address);
        model.addAttribute("categories", shopModel.getAllCategories());
        return new ModelAndView("/index", "resultObject", people);
    }
}
