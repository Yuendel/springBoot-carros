package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros() {
        List<Carro> carros = rep.findAll();

        return carros.stream().map(CarroDTO::create).collect(Collectors.toList());

    }

    public Optional<CarroDTO> getCarroById(Long id){
        return rep.findById(id).map(CarroDTO::create);
    }

    public Optional<Carro> getCarroById2(Long id){
        return rep.findById(id);
    }



//    public List<Carro> getCarrosFeitos(){
//        List<Carro> carros = new ArrayList<>();
//
//        carros.add(new Carro(1L,"Fusca"));
//        carros.add(new Carro(2L,"Brasilia"));
//        carros.add(new Carro(3L,"Chevette"));
//
//        return carros;
//    }

    public List<Carro> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo);
    }

    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(),"Não foi possivel inserir o registro");
        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro");

        Optional<Carro> optional = rep.findById(id);

        if(optional.isPresent()){
            Carro db = optional.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());

            rep.save(db);

            return CarroDTO.create(db);
        }else{
           return null;
        }
    }

    public Boolean delete(Long id) {

        if(getCarroById(id).isPresent()) {
            rep.deleteById(id);
            return true;
         }else{
            return false;
        }

    }
}
