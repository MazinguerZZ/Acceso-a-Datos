package org.dam2.seguros;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.dam2.utilidadeshibernate.GenericJPADAO;

@SuppressWarnings("unchecked")
public class AppSeguros {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericJPADAO<Seguro, Integer> seguroDAO;
		GenericJPADAO<AsistenciaMedica, Integer> asistenciaDAO;
		String query;
		Stream<Object[]> streamArray;
		
		
		seguroDAO = new GenericJPADAO<>(Seguro.class, "seguros");
		asistenciaDAO = new GenericJPADAO<>(AsistenciaMedica.class, "seguros");
		
		List<AsistenciaMedica> asistencias = cargarDatos();
		
		Seguro s3;
		s3 = Seguro.builder().
				idSeguro(3).
				ape1("Sanchez").
				ape2("Gonzalez").
				casado(true).
				coberturas(Coberturas.builder().
								dental(true).
								fecundacionInVitro(false).
								oftalmologia(true).
								build()).
				edad(68).
				embarazada(false).
				enfermedades(Enfermedades.builder().
								alergia(true).
								nombreAlergia("polen olivo").
								estomacal(false).
								build()).
				fechaCreacion(LocalDate.now().minusWeeks(5)).
				nif("003").
				nombre("Juan").
				nunHijos(3).
				sexo(Sexo.HOMBRE).
				build();
		
		seguroDAO.save(s3);
		
		asistencias.forEach(asistenciaDAO::save);
		
		/*
		query = "SELECT s FROM Seguro s";
		seguroDAO.executeQuery(query).
								forEach(System.out::println);
		*/
		
		/*
		query = "SELECT s.nif,s.nombre FROM Seguro s";
		streamArray= seguroDAO.executeQuery(query);
		streamArray.forEach(o -> System.out.println(o[0] + "," + o[1]));
		*/					 
		
		/*
		query = "SELECT s.nif FROM Seguro s "+ 
					"WHERE s.nombre=?1 AND s.ape1=?2 AND s.ape2=?3";
		seguroDAO.executeQuery(query, "Miguel","Sutil", "Martin").
								forEach(System.out::println);
		*/
		/*
		query = "SELECT a FROM AsistenciaMedica a "+
					"WHERE a.importe >=?1";
		asistenciaDAO.executeQuery(query, 120f).
						forEach(System.out::println);
		*/
		
		/*
		query ="SELECT a.idAsistenciaMedica FROM AsistenciaMedica a "+
				"WHERE a.importe BETWEEN ?1 AND ?2";
		
		
		asistenciaDAO.executeQuery(query, 100f,120f).
			forEach(System.out::println);
		*/
		
		/*
		query = "SELECT SUM(a.importe) FROM AsistenciaMedica a";
		asistenciaDAO.executeQuery(query).
			forEach(System.out::println);
		asistenciaDAO.executeQuerySingleResult(query).
		ifPresentOrElse(
				System.out::println, 
				() ->System.out.println("sin asistencias médicas"));
		*/
		/*
		query = "SELECT AVG(a.importe) FROM AsistenciaMedica a";
		asistenciaDAO.executeQuery(query).
			forEach(System.out::println);
		
		query = "SELECT COUNT(s) FROM Seguro s";
		seguroDAO.executeQuery(query).
								forEach(System.out::println);
		*/
		
		/*
		query = "SELECT a.seguro.nif, COUNT(*) FROM AsistenciaMedica a GROUP BY a.seguro";
		streamArray= seguroDAO.executeQuery(query);
		streamArray.forEach(o -> System.out.println(o[0] + "," + o[1]));
		*/
		
		/*
		query = "SELECT s.enfermedades.nombreAlergia FROM Seguro s";
		seguroDAO.executeQuery(query).
						forEach(System.out::println);
		*/
		
		query = "SELECT s.idSeguro, a.idAsistenciaMedica FROM AsistenciaMedica a " + 
					"LEFT JOIN a.seguro s";
		streamArray =asistenciaDAO.executeQuery(query);
		streamArray.forEach(o->System.out.println(o[0] +","+o[1]));
		
		
	}

	public static List<AsistenciaMedica> cargarDatos() {
		// TODO Auto-generated method stub
		List<AsistenciaMedica> asistencias = new ArrayList<>();
		
		AsistenciaMedica a1,a2,a3,a4;
		Seguro s1,s2,s3;
		
		
		
		
		s1 = Seguro.builder().
						idSeguro(1).
						ape1("Sutil").
						ape2("Martin").
						casado(false).
						coberturas(Coberturas.builder().
										dental(false).
										fecundacionInVitro(false).
										oftalmologia(true).
										build()).
						edad(45).
						embarazada(false).
						enfermedades(Enfermedades.builder().
										alergia(true).
										nombreAlergia("Al trabajo").
										build()).
						fechaCreacion(LocalDate.now().minusWeeks(45)).
						nif("001").
						nombre("Miguel").
						nunHijos(2).
						sexo(Sexo.HOMBRE).
						build();
		
	
		s2 = Seguro.builder().
				idSeguro(2).
				ape1("Cordero").
				ape2("Gonzalez").
				casado(false).
				coberturas(Coberturas.builder().
								dental(true).
								fecundacionInVitro(false).
								oftalmologia(true).
								build()).
				edad(48).
				embarazada(false).
				enfermedades(Enfermedades.builder().
								alergia(true).
								nombreAlergia("renitis").
								estomacal(true).
								build()).
				fechaCreacion(LocalDate.now().minusWeeks(5)).
				nif("002").
				nombre("Rosa").
				nunHijos(2).
				sexo(Sexo.MUJER).
				build();

		
		
		a1 = AsistenciaMedica.builder().
				breveDescripcion("a1").
				explicacion("la primera").
				fechaYHora(LocalDateTime.now()).
				importe(100).
				lugar("Maestranza").
				tipoAsistencia("normal").
				seguro(s1).
				build();
		
		a2 = AsistenciaMedica.builder().
				breveDescripcion("a2").
				explicacion("la segunda").
				fechaYHora(LocalDateTime.now().minusDays(3)).
				importe(200).
				lugar("Maestranza").
				tipoAsistencia("urgente").
				seguro(s1).
				build();
		
		a3 = AsistenciaMedica.builder().
				breveDescripcion("a3").
				explicacion("la tercera").
				fechaYHora(LocalDateTime.now().minusMonths(2)).
				importe(100).
				lugar("La Paz").
				tipoAsistencia("normal").
				seguro(s2).
				build();
		
		a4 = AsistenciaMedica.builder().
				breveDescripcion("a4").
				explicacion("la cuarta").
				fechaYHora(LocalDateTime.now()).
				importe(130).
				lugar("Maestranza").
				tipoAsistencia("revision").
				seguro(s2).
				build();
		
		
		asistencias.add(a1);
		asistencias.add(a2);
		asistencias.add(a3);
		asistencias.add(a4);
		
		return asistencias;
	}

	
}
