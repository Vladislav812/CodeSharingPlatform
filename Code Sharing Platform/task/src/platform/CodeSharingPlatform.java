package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
@RestController
@Controller
public class CodeSharingPlatform {
    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }

    static String s = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
    static List<String> codeLib = new ArrayList<>();
    static List<String> dateLib = new ArrayList<>();

    @GetMapping("api/code/{num}")
    HashMap<String, String> returnCode(@PathVariable int num) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            String codeDate = dateLib.get(num - 1);
            String codeTxt = codeLib.get(num - 1);
            hashMap.put("date", codeDate);
            hashMap.put("code", codeTxt);
            return hashMap;
        } catch (Exception IndexOutOfBounds) {
            return hashMap;
        }
    }

    @GetMapping("api/code/latest")
    List<HashMap<String, String>> returnLatest() {
        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        int lower = codeLib.size() >= 10 ? codeLib.size() - 10 : 0;
        for (int i = codeLib.size() - 1; i >= lower; i--) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("code", codeLib.get(i));
            hashMap.put("date", dateLib.get(i));
            hashMapList.add(hashMap);
        }
        return hashMapList;
    }

    @GetMapping("code/latest")
    public String returnLatestHTML() {
        StringBuilder page = new StringBuilder();
        int lower = codeLib.size() >= 10 ? codeLib.size() - 10 : 0;
        for (int i = codeLib.size() - 1; i >= lower; i--) {
            page.append("<html>\n<head>\n    <title>Latest</title>\n</head>\n<body>\n" +
                    "<span style=\"color: green\" id=\"load_date\">" + dateLib.get(i) + "</span>" + "<br>" +
                    "    <pre style=\"background-color: lightgray; border: thin solid black; display: inline-block\"" +
                    "id=\"code_snippet\">\n" + codeLib.get(i) +
                    "</pre>\n" + "</body>\n</html>");
            if (i >= 1) {
                page.append("<br>");
            }
        }
        return page.toString();
    }


    @GetMapping("/code/{num}")
    public String getHTMLCode(@PathVariable int num) {
        if (!codeLib.isEmpty()) {
            return "<html>\n<head>\n    <title>Code</title>\n</head>\n<body>\n" +
                    "<span style=\"color: green\" id=\"load_date\">" + dateLib.get(num - 1) + "</span>" + "<br>" +
                    "    <pre style=\"background-color: lightgray; border: thin solid black; display: inline-block\"" +
                    "id=\"code_snippet\">\n" + codeLib.get(num - 1) +
                    "</pre>\n" + "</body>\n</html>";
        } else {
            //System.out.println(codeText.get("code"));
            return "<html>\n<head>\n    <title>Code</title>\n</head>\n<body>\n" +
                    "<span style=\"color: green\" id=\"load_date\">" + new ActualDate().getActDate() + "</span>" + "<br>" +
                    "    <pre style=\"background-color: lightgray; border: thin solid black; display: inline-block\"" +
                    "id=\"code_snippet\">\n" +
                    "</pre>\n" + "</body>\n</html>";
        }


    }

    @PostMapping("api/code/new")
    public HashMap<String, String> submitNewCode(@RequestBody HashMap<String, String> inputForm) {
        String dt = new ActualDate().getActDate();
        dateLib.add(dt);
        codeLib.add(inputForm.get("code"));
        HashMap<String, String> id = new HashMap<>();
        String index = String.valueOf(dateLib.size());
        id.put("id", index);
        return id;
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
