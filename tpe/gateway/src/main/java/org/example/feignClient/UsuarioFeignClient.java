package org.example.feignClient;

import org.example.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8001/api/users")
public interface UsuarioFeignClient {

    @GetMapping("/buscarPorEmail")
    UsuarioDTO getUsuarioByEmail(@RequestParam("email") String email);
}
