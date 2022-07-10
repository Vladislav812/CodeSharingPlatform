package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.HTML;
import java.util.Map;

@SpringBootApplication
@RestController
@Controller
public class CodeSharingPlatform {

    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    Map<String, String> apiCode = Map.of("code", "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}");
    @GetMapping("/api/code")
    Map<String, String> getApiCode(){
        return apiCode;
    }

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getHTMLCode() {
        return "<html>\n<head>\n    <title>Code</title>\n</head>\n<body>\n" +
                "    <pre>\npublic static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n}" +
                "</pre>\n" + "</body>\n</html>";
    }

    //alternative methods through ModelAndView class:
    @RequestMapping(method = RequestMethod.GET, value = "/fileHTML")
    public ModelAndView getFile() {
        ModelAndView mdv = new ModelAndView();
        mdv.setViewName("codeFile.html");
        return mdv;
    }

    @RequestMapping(method = RequestMethod.GET, value = "codeJSON")
    public ModelAndView getJSON() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("codeJSON.json");
        return modelAndView;
    }



}
