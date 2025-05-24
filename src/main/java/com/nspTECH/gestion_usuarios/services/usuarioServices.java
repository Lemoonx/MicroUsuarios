package com.nspTECH.gestion_usuarios.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nspTECH.gestion_usuarios.DTO.pedidoDTO;
import com.nspTECH.gestion_usuarios.model.usuario;
import com.nspTECH.gestion_usuarios.repository.usuariosRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class usuarioServices {

    private final WebClient webClient;

    public usuarioServices(WebClient webClient){
        this.webClient = webClient;
    }

    @Autowired
    private usuariosRepository usuariosrepository;

    public pedidoDTO buscarpedido(long ID_PEDIDO){
    pedidoDTO pedidodto = webClient.get()
                                                .uri("/{ID_PEDIDO}",ID_PEDIDO)
                                                    .retrieve()
                                                    .bodyToMono(pedidoDTO.class)
                                                    .block();
    return pedidodto;
    }



    public List<usuario> BuscarTodoUsuario(){
        return usuariosrepository.findAll();
    }

    public usuario BuscarUnUsuario(Long ID_USUARIO){
        return usuariosrepository.findById(ID_USUARIO).get();

    }

    public usuario GuardarUsuario(usuario usuario){
        return usuariosrepository.save(usuario);

    }

    public void EliminarUsuario(Long ID_USUARIO){
        usuariosrepository.deleteById(ID_USUARIO);
    }

}
