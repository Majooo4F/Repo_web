// package com.example.equipo.controller;

// import com.example.equipo.dto.MisCursoDTO;
// import com.example.equipo.service.MisCursosService;
// import org.springframework.security.core.Authentication;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.List;

// /**
//  * Endpoint del panel "Mis Cursos" (HU09).
//  *
//  * Ruta protegida: 'Authentication' trae al usuario logueado (su correo) y
//  * depende de que HU02 (login con Spring Security) ya proteja esta ruta.
//  *
//  * NOTA: si HU02 aún no está integrado y quieres probar HU09 por separado,
//  * cambia temporalmente la firma a:
//  *     misCursos(@RequestParam String correo)
//  * y devuelve misCursosService.obtenerMisCursos(correo);
//  * Recuerda revertirlo antes de entregar, porque rompe la protección.
//  */
// @RestController
// @RequestMapping("/api/mis-cursos")
// public class MisCursosController {

//     private final MisCursosService misCursosService;

//     public MisCursosController(MisCursosService misCursosService) {
//         this.misCursosService = misCursosService;
//     }

//     @GetMapping
//     public List<MisCursoDTO> misCursos(Authentication authentication) {
//         String correo = authentication.getName(); // correo del usuario logueado
//         return misCursosService.obtenerMisCursos(correo);
//     }
// }



package com.example.equipo.controller;

import com.example.equipo.dto.MisCursoDTO;
import com.example.equipo.service.MisCursosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoint del panel "Mis Cursos" (HU09).
 *
 * Versión SIN Spring Security (HU02 aún no integrado): recibe el correo
 * como parámetro para poder probar HU09 de forma aislada.
 *
 * Cuando HU02 esté listo y el pom incluya spring-boot-starter-security,
 * vuelve a la versión protegida:
 *     public List<MisCursoDTO> misCursos(Authentication authentication) {
 *         return misCursosService.obtenerMisCursos(authentication.getName());
 *     }
 */
@RestController
@RequestMapping("/api/mis-cursos")
public class MisCursosController {

    private final MisCursosService misCursosService;

    public MisCursosController(MisCursosService misCursosService) {
        this.misCursosService = misCursosService;
    }

    @GetMapping
    public List<MisCursoDTO> misCursos(@RequestParam String correo) {
        return misCursosService.obtenerMisCursos(correo);
    }
}