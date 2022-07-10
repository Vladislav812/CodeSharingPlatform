package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@Controller
public class CodeSharingPlatform {
    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    static HashMap<String, String> codeText = new HashMap<>();
    static String s = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";

    @GetMapping("/api/code")
    Map<String, String> returnJson() {
        if (codeText.isEmpty()) {
            codeText.put("code", s);
            codeText.put("date", new ActualDate().getActDate());
        }
        return codeText;
    }

    @GetMapping("/code")
    public String getHTMLCode() {
        if (codeText.isEmpty()) {
            return "<html>\n<head>\n    <title>Code</title>\n</head>\n<body>\n" +
                    "<span style=\"color: green\" id=\"load_date\">" + new ActualDate().getActDate() + "</span>" + "<br>" +
                    "    <pre style=\"background-color: lightgray; border: thin solid black; display: inline-block\"" +
                    "id=\"code_snippet\">\n" + s +
                    "</pre>\n" + "</body>\n</html>";
        } else {
            System.out.println(codeText.get("code"));
            return "<html>\n<head>\n    <title>Code</title>\n</head>\n<body>\n" +
                    "<span style=\"color: green\" id=\"load_date\">" + new ActualDate().getActDate() + "</span>" + "<br>" +
                    "    <pre style=\"background-color: lightgray; border: thin solid black; display: inline-block\"" +
                    "id=\"code_snippet\">\n" + codeText.get("code") +
                    "</pre>\n" + "</body>\n</html>";
        }


    }

    @PostMapping("api/code/new")
    public Map<String, String> returnNew(@RequestBody HashMap<String, String> inputForm) {
        codeText.clear();
        codeText.put("code", inputForm.get("code"));
        codeText.put("date", new ActualDate().getActDate());
        return new HashMap<>();
    }

    @GetMapping("code/new")
    public String returnSubmitForm() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Create</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<textarea id=\"code_snippet\">\n" +
                "</textarea>\n" +
                "<button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>\n" +
                "<script>\n" +
                "    function send() {\n" +
                "        let object = {\n" +
                "            \"code\": document.getElementById(\"code_snippet\").value\n" +
                "        };\n" +
                "        let json = JSON.stringify(object);\n" +
                "        let xhr = new XMLHttpRequest();\n" +
                "        xhr.open(\"POST\", '/api/code/new', false)\n" +
                "        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "        xhr.send(json);\n" +
                "        if (xhr.status == 200) {\n" +
                "            alert(\"Success!\");\n" +
                "        }\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
    }

}
