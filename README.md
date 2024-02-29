# webII-Jpa

No núcleo do projeto, na classe 'Main', estão implementados os testes do CRUD (Create, Read, Update, Delete) para cada entidade, bem como as consultas específicas, que incluem:

- Identificação dos autores de cada livro.
- Identificação dos livros associados a cada autor.
- Identificação das editoras de cada livro.
- Identificação dos livros relacionados a cada editora.
- Identificação dos autores associados a cada editora.
- Identificação das editoras associadas a cada autor.

Para estabelecer a conexão com o banco de dados, configuramos o arquivo 'hibernate.cfg.xml' localizado na pasta 'resources'. Utilizamos o MySQL, com um banco de dados denominado 'JPA'. Caso haja necessidade, é possível alterar as informações de acesso ao banco diretamente nesse arquivo.

Para testar e verificar o funcionamento de cada operação mencionada acima, basta executar todos os métodos na classe 'Main'. Alternativamente, é possível executar individualmente cada método criado.
