package br.com.systemit.erp.auth.repository.specs;

import br.com.systemit.erp.auth.model.Modulo;
import org.springframework.data.jpa.domain.Specification;

public class ModuloSpecs {

    public static Specification<Modulo> idEqual(Integer id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id.toString());
    }

    public static Specification<Modulo> nomeLike(String nome) {
        return (root, query, cb) -> cb.like( cb.upper(root.get("nome")), "%"+nome.toUpperCase()+"%");
    }

//    public static Specification<Modulo> anoPublicacaoEqual(Integer anoPublicacao){
//        // and to_char(data_publicacao, 'YYYY') = :anoPublicacao
//        return (root, query, cb) ->
//                cb.equal( cb.function("to_char", String.class,
//                        root.get("dataPublicacao"), cb.literal("YYYY")),anoPublicacao.toString());
//    }
//
//    public static Specification<Modulo> nomeModuloLike(String nome){
//        return (root, query, cb) -> {
//            Join<Object, Object> joinPerfil = root.join("autor", JoinType.INNER);
//            return cb.like( cb.upper(joinPerfil.get("nome")), "%" + nome.toUpperCase() + "%" );
//
////            return cb.like( cb.upper(root.get("perfil").get("nome")), "%" + nome.toUpperCase() + "%" );
//        };
//    }
}
