package com.jdasilva.socialweb.reclamaciones.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jdasilva.socialweb.reclamaciones.models.domain.Reclamacion;

public interface IReclamacionDaoPageable extends PagingAndSortingRepository<Reclamacion, Long>{

}
