package gh.sample.springboot.Controller;

import gh.sample.springboot.Domain.Hello;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "hello")
public class HelloController {
    @GetMapping()
    public Hello get() {


        return new Hello(0, "Hello Spring Boot!");

    }

    @GetMapping(value = "ge")
    public Hello getMsg() {
        int code = 0;
        String msg = null;
        HashMap<String,String> map;
        map = new HashMap<>();
        for(int i =1 ; i<=10; i++){
            code ++;
            msg += "Hello Geunha"+code+"\n";
            map.put(String.valueOf(code),msg);
            System.out.println(msg);
        }
        return new Hello(code, msg);

    }




}
