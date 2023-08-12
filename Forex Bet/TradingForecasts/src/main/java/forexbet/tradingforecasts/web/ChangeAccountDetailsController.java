package forexbet.tradingforecasts.web;


import forexbet.tradingforecasts.model.dto.ChangeAccountUsernameDTO;
import forexbet.tradingforecasts.service.account.AccountService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChangeAccountDetailsController {

    private final AccountService accountService;

    public ChangeAccountDetailsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ModelAttribute
    public ChangeAccountUsernameDTO changeAccountUsernameDTO() {
        return new ChangeAccountUsernameDTO();
    }

    @GetMapping("/my-account")
    public String getMyAccount() {
        return "change-username";
    }

    @PatchMapping("/my-account")
    public String myAccountChangeUsername(@Valid ChangeAccountUsernameDTO changeAccountUsernameDTO,
                                          BindingResult bindingResult,
                                          RedirectAttributes redirectAttributes,
                                          @AuthenticationPrincipal UserDetails principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("changeAccountUsernameDTO", changeAccountUsernameDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changeAccountUsernameDTO", bindingResult);

            return "redirect:my-account";
        }

        try {
            accountService.editAccountUsername(changeAccountUsernameDTO, principal);
        } catch (Exception e) {
            ObjectError error = new ObjectError("globalError", e.getMessage());
            bindingResult.addError(error);

            redirectAttributes
                    .addFlashAttribute("changeAccountUsernameDTO", changeAccountUsernameDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.changeAccountUsernameDTO", bindingResult);

            return "redirect:my-account";
        }

        return "redirect:/";
    }
}
