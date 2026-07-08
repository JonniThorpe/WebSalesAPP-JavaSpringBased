package com.theenglishcut.controller;

import com.theenglishcut.dao.*;
import com.theenglishcut.dto.Category;
import com.theenglishcut.dto.Product;
import com.theenglishcut.entity.CategoryEntity;
import com.theenglishcut.entity.ProductEntity;
import com.theenglishcut.entity.UserEntity;
import com.theenglishcut.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RolRepository rolRepository;

    /**
     * Devuelve la pagina login para acceder con un usuario
     * @return login.jsp
     */
    @GetMapping("/login")
    public String doLogin () {
        return "/login";
    }

    @PostMapping("/loginUser")
    public String login(Model modelo, @RequestParam(value = "nombre",required = false)String user, @RequestParam(value = "password",required = false)String password, HttpSession sesion){

        UserEntity userEntity = usuarioRepository.findByNombreUser(user);

        if(user == null){
            modelo.addAttribute("error","no existe ese usuario, prueba a registrarte antes de hacer login");
            return "/error?tipo=login";
        }else if(password == null || !password.equals(userEntity.getPassword())){
            modelo.addAttribute("error","la contraseña no coincide, prueba a prueba otra contraseña para hacer login hacer login");
            return "/error?tipo=login";
        }

        sesion.setAttribute("user", userEntity);

        //control del carrito
        Map<Product,Integer> basketProducts = (Map<Product, Integer>) sesion.getAttribute("basketProducts");
        if(basketProducts != null){
            basketProducts.clear();
        }else {
            sesion.setAttribute("basketProducts", new Hashtable<>());
        }
        return "redirect:/";
    }

    /**
     * When a user hasn't been loged in and the role is not Admin,
     * then the only option to register a user is as role User
     * @param sesion
     * @param modelo
     * @return
     */
    @GetMapping("/register")
    public String doRegister (HttpSession sesion, Model modelo  ) {

        UserEntity user = (UserEntity) sesion.getAttribute("user");


        if(user != null && user.getRol().getNombre().equals("Administrador")){
            modelo.addAttribute("roles", rolRepository.findAll());
        }else{
            modelo.addAttribute("roles", Collections.EMPTY_LIST);
        }

        return "/register";
    }

    @PostMapping("/registerUser")
    public String register(HttpSession sesion, Model modelo, @RequestParam(value = "nombre",required = false)String user, @RequestParam(value = "password",required = false)String password, @RequestParam(value="rolId")Integer rolId){

        UserEntity userEntity = usuarioRepository.findByNombreUser(user);

        if(userEntity != null){
            modelo.addAttribute("error","ya existe ese usuario, prueba a registrarte con otro nombre de usuario");
            return "redirect:/error?tipo=register";
        }

        userEntity = new UserEntity();

        userEntity.setNombre(user);
        userEntity.setPassword(password);

        userEntity.setRol(rolRepository.findById(rolId).get());
        usuarioRepository.save(userEntity);

        //designamoos como el nuevo usuario en uso para la web
        sesion.setAttribute("user", userEntity);

        sesion.setAttribute("basketProducts", new Hashtable<>());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout (HttpSession sesion) {

        sesion.setAttribute("user",null);
        sesion.setAttribute("basketProducts",new Hashtable<>());
        return "redirect:/";
    }

    @GetMapping("/error")
    public String doError (@RequestParam("tipo")String tipo, Model modelo) {
        modelo.addAttribute("tipo",tipo);
        return "/error";
    }

    /**
     * Metodo que devuelve el incio de la página
     * @return home.jsp
     */
    @GetMapping("/")
    public String doInicio (HttpSession sesion) {
        sesion.setAttribute("categoryListView", categoryService.findAll());
        Map<Product,Integer> basketProducts = (Map<Product, Integer>) sesion.getAttribute("basketProducts");
        if(basketProducts == null){
            sesion.setAttribute("basketProducts", new Hashtable<>());
        }
        return "/home";
    }
}
