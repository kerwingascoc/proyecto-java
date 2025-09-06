package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.model.dto.*;

import java.util.List;

public interface PedidoService {
    List<PedidoDto> listAll();
    PedidoDto findById(Long id);
    PedidoSaveResponseDto save(PedidoSaveRequestDto request);
    PedidoDto update(Long id, PedidoSaveRequestDto request);
    void deleteById(Long id);
}
