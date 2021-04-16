package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller -> Spring이 자동으로 관리하는 대상
 */
@Controller
public class HelloController {

    /**
     * getMapping(path)
     */
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");

        /**
         * return시 viewResolver가 화면을 찾아서 처리한다.
         * */
        return "hello";
    }

    /**
     * get param
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * responseBody -> view로 반환이 아닌 body부에 데이터를 직접 반환
     */
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    /**
     * responseBody(인스턴스 반환)
     * viewResolver -> HttpMessageConverter가 동작
     * (StringHttpMessageConverter, MappingJackson2HttpMessageConverter[json])
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
