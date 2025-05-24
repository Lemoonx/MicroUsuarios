package com.nspTECH.gestion_usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nspTECH.gestion_usuarios.DTO.pedidoDTO;
import com.nspTECH.gestion_usuarios.DTO.usuariopedidoDTO;
import com.nspTECH.gestion_usuarios.model.usuario;
import com.nspTECH.gestion_usuarios.services.usuarioServices;




@RestController
@RequestMapping("/api/v1/Usuarios")
public class usuarioController {


@Autowired

    private usuarioServices usuarioservices;

    @GetMapping
    public ResponseEntity<?> ListarUsuarios(){
        List<usuario> usuarios = usuarioservices.BuscarTodoUsuario();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran dato");
        } else {
            return ResponseEntity.ok(usuarios);
        }
    }
    @GetMapping("/{ID_USUARIO}")
    public ResponseEntity<?> BuscarProducto(@PathVariable Long ID_USUARIO){

        try {
            usuario usuarioBuscado = usuarioservices.BuscarUnUsuario(ID_USUARIO);
            return ResponseEntity.ok(usuarioBuscado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Producto");
        }
        
    }

    @GetMapping("/UsuarioPedido/{ID_PEDIDO}")
    public ResponseEntity<?> pedidocliente(@PathVariable Long ID_PEDIDO){

        try {
            usuario usuarioBuscado = usuarioservices.BuscarUnUsuario(ID_PEDIDO);
            pedidoDTO pedido = usuarioservices.buscarpedido(ID_PEDIDO);
            usuariopedidoDTO uspedDTO = new usuariopedidoDTO();
            uspedDTO.setNOMBRE(usuarioBuscado.getNOMBRE());
            uspedDTO.setCORREO(usuarioBuscado.getCORREO());
            uspedDTO.setDIRECCION(usuarioBuscado.getDIRECCION());
            uspedDTO.setVALOR_TOTAL(pedido.getVALOR_TOTAL());
            uspedDTO.setIVA(pedido.getIVA());
            uspedDTO.setANOTACIONES(pedido.getANOTACIONES());
            return ResponseEntity.ok(uspedDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el pedido");
        }
        
    }















    @PostMapping
    public ResponseEntity<?> GuardarUsuario(@RequestBody usuario usuarioGuardar){
    try {
            usuario usuarioRegistrar = usuarioservices.GuardarUsuario(usuarioGuardar);
            return ResponseEntity.ok(usuarioRegistrar);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("No se puede registrar el Producto");
    }
    }
    
    @DeleteMapping("/{ID_USUARIO}")
        public ResponseEntity<String> EliminarUsuario(@PathVariable Long ID_USUARIO){
            try {
                usuario usuarioBuscado = usuarioservices.BuscarUnUsuario(ID_USUARIO);
                usuarioservices.EliminarUsuario(ID_USUARIO);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimina Usuario");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no esta registrado");
            }
        }
    @PutMapping("/{ID_USUARIO}") //SOLO PERMITE ACTUALIZAR ESCRIBIENDO TODOS LOS DATOS
        
    public ResponseEntity<?> ActualizarUsuarios(@PathVariable Long ID_USUARIO, @RequestBody usuario usuarioActualizar){
        try {
            usuario usuarioActualizado = usuarioservices.BuscarUnUsuario(ID_USUARIO);
            usuarioActualizado.setNOMBRE(usuarioActualizar.getNOMBRE());
            usuarioActualizado.setCORREO(usuarioActualizar.getCORREO());
            usuarioActualizado.setDIRECCION(usuarioActualizar.getDIRECCION());
            usuarioActualizado.setTELEFONO(usuarioActualizar.getTELEFONO());
            usuarioActualizado.setCONTRASENA(usuarioActualizar.getCONTRASENA());
            usuarioservices.GuardarUsuario(usuarioActualizado);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no esta registrado");
        }
    }
    









}
