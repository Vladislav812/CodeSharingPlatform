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

    static List<String> codeLib = new ArrayList<>();
    static List<String> dateLib = new ArrayList<>();

    @GetMapping("api/code/{num}")
    public static HashMap<String, String> returnCode(@PathVariable int num) {
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

    @PostMapping("api/code/new")
    public HashMap<String, String> submitNewCode(@RequestBody HashMap<String, String> inputForm) {
        String dateTime = new ActualDate().getActDate();
        dateLib.add(dateTime);
        codeLib.add(inputForm.get("code"));
        HashMap<String, String> newSnippet = new HashMap<>();
        String index = String.valueOf(dateLib.size());
        newSnippet.put("id", index);
        return newSnippet;
    }

    @GetMapping("api/code/latest")
    public static List<HashMap<String, String>> returnLatest() {
        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        int size = codeLib.size();
        int lower = size >= 10 ? size - 10 : 0;
        for (int i = size - 1; i >= lower; i--) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("code", codeLib.get(i));
            hashMap.put("date", dateLib.get(i));
            hashMapList.add(hashMap);
        }
        return hashMapList;
    }


}
