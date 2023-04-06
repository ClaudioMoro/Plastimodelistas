/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmt.plastimodelistasdetucuman.servicios;

import com.pmt.plastimodelistasdetucuman.repositorios.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cmoro1
 */

@Service
public class ImagenServicio {
    @Autowired
    private ImagenRepositorio imagenRepositorio;

}
