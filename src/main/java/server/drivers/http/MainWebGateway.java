package server.drivers.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainWebGateway {

    @GetMapping("/")
    public String landingMessage() {
        return "Welcome to Cupet";
    }

    @GetMapping("/authtest")
    public String authTest(HttpServletRequest req) {
        return "You are authenticated! Welcome " + req.getHeader("userId");
    }

}
