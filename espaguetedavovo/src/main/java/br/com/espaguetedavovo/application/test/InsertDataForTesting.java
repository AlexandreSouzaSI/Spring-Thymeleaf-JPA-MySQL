package br.com.espaguetedavovo.application.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import antlr.StringUtils;
import br.com.espaguetedavovo.application.service.RestauranteService;
import br.com.espaguetedavovo.domain.cliente.Cliente;
import br.com.espaguetedavovo.domain.cliente.ClienteRepository;
import br.com.espaguetedavovo.domain.restaurante.CategoriaRestaurante;
import br.com.espaguetedavovo.domain.restaurante.CategoriaRestauranteRepository;
import br.com.espaguetedavovo.domain.restaurante.ItemCardapio;
import br.com.espaguetedavovo.domain.restaurante.ItemCardapioRepository;
import br.com.espaguetedavovo.domain.restaurante.Restaurante;
import br.com.espaguetedavovo.domain.restaurante.RestauranteRepository;

@Component
public class InsertDataForTesting {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CategoriaRestauranteRepository categoriaRestauranteRepository;
	
	@Autowired
	private ItemCardapioRepository itemCardapioRepository;
	
	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		clientes();
		Restaurante[] restaurantes = restaurantes();
		itensCardapio(restaurantes);
	}
	
	private Restaurante[] restaurantes() {
		List<Restaurante> restaurantes = new ArrayList<Restaurante>();
		
		CategoriaRestaurante categoriaTropeiro = categoriaRestauranteRepository.findById(1).orElseThrow();
		CategoriaRestaurante categoriaMassa = categoriaRestauranteRepository.findById(2).orElseThrow();
		CategoriaRestaurante categoriaAlmoco = categoriaRestauranteRepository.findById(3).orElseThrow();
	
		Restaurante r = new Restaurante();
		r.setNome("Espaguete da vovo");
		r.setEmail("espaguete@vovo");
		r.setSenha(br.com.espaguetedavovo.util.StringUtils.encrypt("r"));
		r.setCnpj("12457896325487");
		r.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r.setTelefone("975805400");
		r.getCategorias().add(categoriaTropeiro);
		r.getCategorias().add(categoriaMassa);
		r.getCategorias().add(categoriaAlmoco);
		r.setLogotipo("0001-logotipo");
		r.setTempoEntrega(30);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		r = new Restaurante();
		r.setNome("Almoço");
		r.setEmail("Almoco@vovo");
		r.setSenha(br.com.espaguetedavovo.util.StringUtils.encrypt("r"));
		r.setCnpj("12457896325487");
		r.setTaxaEntrega(BigDecimal.valueOf(3.2));
		r.setTelefone("975805400");
		r.getCategorias().add(categoriaAlmoco);
		r.setLogotipo("0002-almoco");
		r.setTempoEntrega(30);
		restauranteRepository.save(r);
		restaurantes.add(r);
		
		Restaurante[] array = new Restaurante[restaurantes.size()];
		return restaurantes.toArray(array);
	}
	
	private Cliente[] clientes() {
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente c = new Cliente();
		c.setNome("Alexandre Moura");
		c.setEmail("alemoura23@hotmail.com");
		c.setSenha(br.com.espaguetedavovo.util.StringUtils.encrypt("c"));
		c.setCep("32010745");
		c.setCpf("08051357663");
		c.setTelefone("975805400");
		clienteRepository.save(c);
		
		c = new Cliente();
		c.setNome("Nathalia");
		c.setEmail("nathylucena@hotmail.com");
		c.setSenha(br.com.espaguetedavovo.util.StringUtils.encrypt("c"));
		c.setCep("32010745");
		c.setCpf("11317160606");
		c.setTelefone("975805400");
		clienteRepository.save(c);
		
		Cliente[] array = new Cliente[clientes.size()];
		return clientes.toArray(array);
	}
	
	private void itensCardapio(Restaurante[] restaurantes) {
		ItemCardapio ic = new ItemCardapio();
		ic.setCategoria("Tropeiro");
		ic.setDescricao("Arroz, Tropeiro, Bife de porco, Torresmo, Ovo e couve");
		ic.setNome("Tropeiro");
		ic.setPreco(BigDecimal.valueOf(22.2));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(true);
		ic.setImagem("0001-comida.png");
		itemCardapioRepository.save(ic);
		
		ic = new ItemCardapio();
		ic.setCategoria("Massa");
		ic.setDescricao("Espaguete, molho a bolonhesa, mussarela e cebolinha");
		ic.setNome("Espaguete a bolonhesa");
		ic.setPreco(BigDecimal.valueOf(22.2));
		ic.setRestaurante(restaurantes[0]);
		ic.setDestaque(true);
		ic.setImagem("0002-comida.png");
		itemCardapioRepository.save(ic);
		
	}
}
