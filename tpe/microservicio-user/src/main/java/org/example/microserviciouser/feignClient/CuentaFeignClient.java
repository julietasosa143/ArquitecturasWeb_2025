package org.example.microserviciouser.feignClient;

import org.example.microserviciouser.entities.Cuenta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="microservicio-user", url="http://localhost:8001/api/cuentas" )
public interface CuentaFeignClient {

    @PutMapping("/anular/{id}")
    public ResponseEntity<String> anularCuenta(@PathVariable("id") Long id);
}
