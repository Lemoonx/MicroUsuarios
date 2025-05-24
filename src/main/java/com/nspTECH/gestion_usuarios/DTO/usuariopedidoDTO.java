package com.nspTECH.gestion_usuarios.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class usuariopedidoDTO {

    private long ID_PEDIDO;
    private String ANOTACIONES;
    private Long IVA;
    private Long VALOR_TOTAL;
    private String NOMBRE;
    private String CORREO;
    private Long TELEFONO;
    private String DIRECCION;

}
