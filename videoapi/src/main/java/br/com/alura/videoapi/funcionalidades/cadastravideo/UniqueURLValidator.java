package br.com.alura.videoapi.funcionalidades.cadastravideo;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueURLValidator implements ConstraintValidator<UniqueURL, String> {

    private String domainAttribute;
    private Class<?> aClass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueURL constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        aClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("SELECT 1 FROM " + aClass.getName() + " WHERE " +
                domainAttribute + "=:url");
        query.setParameter("url", url);
        List<?> resultado = query.getResultList();
        Assert.state(resultado.size() <= 1,"Foi encontrado mais de uma url como " + aClass.getName() + "no banco de dados" );

        return resultado.isEmpty();
    }
}
