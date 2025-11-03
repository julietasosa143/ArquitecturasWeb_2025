package org.example.microserviciobilling.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facturas")
public class FacturaController {


    private FacturaService facturaService;


}
