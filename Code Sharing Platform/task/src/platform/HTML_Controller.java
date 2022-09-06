package platform;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;

@Controller
public class HTML_Controller {

    @GetMapping("/code/{num}")
    public String getHTMLCode(Model model, @PathVariable int num) {
        HashMap<String, String> hm = CodeSharingPlatform.returnCode(num);
        model.addAttribute("snippet", hm);
        return "Code";
    }

    @GetMapping("code/new")
    public String returnHTML() {
        return "Create";
    }

    @GetMapping("code/latest")
    public String returnLatestHTML(Model model) {
        List<HashMap<String, String>> latestList = CodeSharingPlatform.returnLatest();
        model.addAttribute("latestList_", latestList);
        return "Latest";
    }

}
