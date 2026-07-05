package com.farmacia.farmacia.service;

import java.util.Optional;

import com.farmacia.farmacia.entity.Usuario;

public interface UsuarioService {

    Optional<Usuario> buscarPorUsername(String username);

}