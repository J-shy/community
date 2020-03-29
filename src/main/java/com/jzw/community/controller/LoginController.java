package com.jzw.community.controller;

import com.jzw.community.dto.GithubUser;
import com.jzw.community.dto.ProviderDto;
import com.jzw.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        ProviderDto providerDto = new ProviderDto();
        providerDto.setClient_id(clientId);
        providerDto.setClient_secret(clientSecret);
        providerDto.setRedirect_uri(redirectUri);
        providerDto.setCode(code);
        providerDto.setState(state);
        String accessToken = githubProvider.getAccessToken(providerDto);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName()+user.getBio());
        return "index";
    }
}
