package hu.roszpapad.konyvklub.repositories;

import hu.roszpapad.konyvklub.model.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long>{

    List<Report> findByReported(String reported);
    List<Report> findAll();
}
