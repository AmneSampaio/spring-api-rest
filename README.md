### Readme 

# **Spring-Api-Rest**

## Neste projeto Java você vai encontrar uma estrutura de três camadas que faz um caminho de ida e volta:

##**- Controller/ida**

### Essa camada estabelece os endpoints da aplicação e 

###*(ida)* capta os dados de entrada e os repassa para a camada seguinte,
###e
###*(volta)* recebe o retorno do banco de dados e manda para a camada externa através dos endpoints.
###
##**- Service/ida**
###
###*(ida)* Ao receber os dados, é nessa camada que são realizadas as regras de negócio e também encaminha os resultados para a próxima ###camada.
###
###*(volta)* Ao receber informações do banco de dados repassa para os endpoints presentes na controller.
###
##**- Repository/ida-e-volta**
###
###Por ser uma interface, assina métodos que buscam as informações que pode   
###
###*(ida)* persistir no banco de dados
###ou 
###*(volta)* coletar e responder ao service.


###Há três classes de atributos de usuário diferentes, uma para cada função, com o objetivo de organizar e manter apenas os atributos ###necessários nas classes utilizadas. A classe mais completa que é utilizada na resposta do formato **Json**, é a *Usuario.class*. A ###classe para modelo de criação de usuário (registro/cadastro) é a *UsuarioDTO.class* e para os logins *UsuarioLoginPassword.class*.

###Para que as senhas não fiquem completamente expostas, utilizei um código que faz o encode em Md5 ( EncryptPasswordUtility.java )

###Utilizei também diversas anottations do **Spring**, dentre elas: 

###*@Autowired* : Resolve e injeta dependencias relacionadas ao método aplicado.
###*@GetMapping,@PostMapping,@PutMapping,@DeleteMapping* : Endpoints que podem receber o body da requisição e/ou retornar o que foi pedido ###pelo usuário.
###*@Entity* : Sinaliza que deve ser criada uma tabela da classe relacionada, com todos os atributos presentes. 
###*@Transitional* : No caso de mais de uma alteração em andamento, se uma das alterações não puder ser feita, essa anotattion desfaz ###todas as alterações e volta para o estado de início. 




