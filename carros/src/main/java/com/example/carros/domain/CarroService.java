package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public Iterable<Carro> getCarros() {
        return rep.findAll();

    }

    public Optional<Carro> getCarroById(Long id){
        return rep.findById(id);
    }


    public List<Carro> getCarrosFeitos(){
        List<Carro> carros = new ArrayList<>();

        carros.add(new Carro(1L,"Fusca"));
        carros.add(new Carro(2L,"Brasilia"));
        carros.add(new Carro(3L,"Chevette"));

        return carros;
    }

    public Iterable<Carro> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo);
    }

    public Carro insert(Carro carro) {
        return rep.save(carro);
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro");

        Optional<Carro> optional = getCarroById(id);

        if(optional.isPresent()){
            Carro db = optional.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());

            rep.save(db);

            return db;
        }else{
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void delete(Long id) {
        Optional<Carro> optional = getCarroById(id);

        if(optional.isPresent()) {
            rep.deleteById(id);
         }else{
            throw new RuntimeException("Não foi possivel deletar o registro");
        }



    }
}
