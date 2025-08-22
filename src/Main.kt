enum class Genero { MASCULINO, FEMININO, INDEFINIDO }

data class Pessoa(
    val nome: String,
    val idade: Int,
    val cidade: String,
    val empregado: Boolean,
    val genero: Genero
)

fun main() {
    val pessoas = listOf(
        Pessoa("Alice",   25, "São Paulo",      true,  Genero.FEMININO),
        Pessoa("Bruno",   30, "Rio de Janeiro", false, Genero.MASCULINO),
        Pessoa("Carla",   22, "Belo Horizonte", true,  Genero.FEMININO),
        Pessoa("Daniel",  40, "Curitiba",       true,  Genero.MASCULINO),
        Pessoa("Eduarda", 19, "São Paulo",      false, Genero.FEMININO),
        Pessoa("Felipe",  28, "Porto Alegre",   true,  Genero.MASCULINO),
        Pessoa("Gabriela",35, "São Paulo",      true,  Genero.FEMININO),
        Pessoa("Henrique",50, "Rio de Janeiro", false, Genero.MASCULINO),
        Pessoa("Isabela", 27, "Recife",         true,  Genero.FEMININO),
        Pessoa("Jordan",  33, "Fortaleza",      false, Genero.INDEFINIDO)
    )

    val exercicios: List<Pair<String, (List<Pessoa>) -> Any?>> = listOf(
        "01) Imprimir todos os nomes" to { p -> exercicio1(p) },
        "02) Imprimir 'Nome - Gênero' para cada pessoa" to { p -> exercicio2(p) },
        "03) Contar quantas pessoas existem" to { p -> exercicio3(p) },
        "04) Mostrar a primeira pessoa" to { p -> exercicio4(p) },
        "05) Mostrar a última pessoa" to { p -> exercicio5(p) },
        "06) Mostrar a segunda pessoa" to { p -> exercicio6(p) },
        "07) Verificar se a lista está vazia" to { p -> exercicio7(p) },

        "08) Filtrar apenas pessoas do gênero FEMININO" to { p -> exercicio8(p) },
        "09) Filtrar pessoas de São Paulo do gênero FEMININO" to { p -> exercicio9(p) },
        "10) Filtrar pessoas do gênero FEMININO desempregadas" to { p -> exercicio10(p) },
        "11) Filtrar pessoas do gênero MASCULINO com menos de 30 anos" to { p -> exercicio11(p) },
        "12) Verificar se existe algum MASCULINO desempregado" to { p -> exercicio12(p) },
        "13) Verificar se TODAS as pessoas do gênero FEMININO têm 18+ anos" to { p -> exercicio13(p) },

        "14) Listar apenas os nomes das pessoas do gênero FEMININO" to { p -> exercicio14(p) },
        "15) Gerar frases: 'Nome (GÊNERO) tem X anos e mora em Cidade'" to { p -> exercicio15(p) },
        "16) Ordenar por gênero (enum) e depois por nome" to { p -> exercicio16(p) },
        "17) Ordenar por cidade e, dentro de cada cidade, por idade decrescente" to { p -> exercicio17(p) },

        "18) Somar idades das pessoas do gênero MASCULINO" to { p -> exercicio18(p) },
        "19) Calcular idade média por gênero" to { p -> exercicio19(p) },
        "20) Encontrar a pessoa mais velha do gênero FEMININO" to { p -> exercicio20(p) },
        "21) Encontrar a pessoa mais nova do gênero INDEFINIDO (se houver)" to { p -> exercicio21(p) },

        "22) (Composição) SP → nomes em ordem alfabética" to { p -> exercicio22(p) },
        "23) (Composição) Por cidade: quantas FEMININO estão empregadas" to { p -> exercicio23(p) },
        "24) (Composição) Mapa: gênero → lista de nomes ordenados por idade crescente" to { p -> exercicio24(p) },

        "25) Encontrar a primeira pessoa de Rio de Janeiro" to { p -> exercicio25(p) },
        "26) Encontrar a última pessoa de São Paulo" to { p -> exercicio26(p) },
        "27) Encontrar a primeira pessoa desempregada" to { p -> exercicio27(p) },
        "28) Encontrar a última pessoa do gênero INDEFINIDO" to { p -> exercicio28(p) },
        "29) Encontrar a primeira pessoa com mais de 40 anos" to { p -> exercicio29(p) },
        "30) Encontrar a última pessoa com idade menor que 25" to { p -> exercicio30(p) },
    )

    exercicios.forEachIndexed { idx, (titulo, funcao) ->
        runCatching {
            val resultado = funcao(pessoas)
            println("==== Exercício ${idx + 1} ====")
            println(titulo)
            println(formatarResultado(resultado))
            println()
        }
    }
}

private fun formatarResultado(resultado: Any?): String = when (resultado) {
    null -> "(sem resultado)"
    is String, is Number, is Boolean -> resultado.toString()
    is Pessoa -> resultado.toString()
    is Genero -> resultado.toString()
    is Pair<*, *> -> "(${formatarResultado(resultado.first)} , ${formatarResultado(resultado.second)})"
    is Triple<*, *, *> -> "(${formatarResultado(resultado.first)} , ${formatarResultado(resultado.second)} , ${formatarResultado(resultado.third)})"
    is List<*> -> if (resultado.isEmpty()) "[]"
    else resultado.joinToString(prefix = "[\n", postfix = "\n]", separator = "\n") { "  - ${formatarResultado(it)}" }
    is Map<*, *> -> if (resultado.isEmpty()) "{}"
    else resultado.entries.joinToString(prefix = "{\n", postfix = "\n}", separator = "\n") {
        "  ${formatarResultado(it.key)} -> ${formatarResultado(it.value)}"
    }
    else -> resultado.toString()
}

// Exercícios: implemente as funções conforme contratos/requisitos descritos.

fun exercicio1(pessoas: List<Pessoa>): List<Pessoa> {
    //percorrer a lista imprimindo o nome de cada pessoa, e retornar a própria lista.
    pessoas.forEach { println(it.nome) }
    return pessoas
}

fun exercicio2(pessoas: List<Pessoa>): List<String> {
    //retornar List<String> no formato "Nome - Genero"
    val lista = pessoas.map { "${it.nome} - ${it.genero}" }
    return lista

}

fun exercicio3(pessoas: List<Pessoa>): Int {
    //retornar Int com a quantidade de pessoas da lista
    val quantidadePessoas = pessoas.size
    return quantidadePessoas
}

fun exercicio4(pessoas: List<Pessoa>): Pessoa? {
    //retornar a primeira Pessoa da lista (ou null se a lista estiver vazia)
    val primeiraPessoa = pessoas.firstOrNull()
    return primeiraPessoa
}

fun exercicio5(pessoas: List<Pessoa>): Pessoa? {
    //retornar a última Pessoa da lista (ou null se a lista estiver vazia),
    val ultimaPessoa = pessoas.lastOrNull()
    return ultimaPessoa
}

fun exercicio6(pessoas: List<Pessoa>): Pessoa? {
    //retornar a segunda Pessoa da lista (ou null se não houver)
    val segundaPessoa = pessoas.getOrNull(1)
    return segundaPessoa
}

fun exercicio7(pessoas: List<Pessoa>): Boolean {
    //retornar Boolean indicando se a lista está vazia
    val listaEstaVazia = pessoas.isEmpty()
    return listaEstaVazia
}

fun exercicio8(pessoas: List<Pessoa>): List<Pessoa> {
    //retornar List<Pessoa> apenas do gênero FEMININO
    val listaGeneroFeminino = pessoas.filter { it.genero == Genero.FEMININO }
    return listaGeneroFeminino
}

fun exercicio9(pessoas: List<Pessoa>): List<Pessoa> {
    // retornar List<Pessoa> de São Paulo E do gênero FEMININO
    val mulheresDeSp  = pessoas.filter { it.genero == Genero.FEMININO && it.cidade == "São Paulo" }

    return mulheresDeSp
}

fun exercicio10(pessoas: List<Pessoa>): List<Pessoa> {
    //retornar List<Pessoa> do gênero FEMININO que NÃO estão empregadas
    val listaMulheresDesempregada = pessoas.filter { it.genero == Genero.FEMININO && !it.empregado }
    return listaMulheresDesempregada
}

fun exercicio11(pessoas: List<Pessoa>): List<Pessoa> {
    //retornar List<Pessoa> do gênero MASCULINO com idade < 30
    val menoresDe30 = pessoas.filter { it.genero == Genero.MASCULINO && it.idade < 30 }
    return menoresDe30
}

fun exercicio12(pessoas: List<Pessoa>): Boolean {
    //retornar Boolean: existe algum MASCULINO desempregado?
    val HomenDesempregado = pessoas.any { it.genero == Genero.MASCULINO && !it.empregado }
    return HomenDesempregado
}

fun exercicio13(pessoas: List<Pessoa>): Boolean {
    //retornar Boolean: todas do FEMININO têm 18+?
    return pessoas
        .filter { it.genero == Genero.FEMININO }
        .all { it.idade >= 18 }
}

fun exercicio14(pessoas: List<Pessoa>): List<String> {
    //retornar List<String> com nomes das pessoas do gênero FEMININO
    val listaDeNomes = pessoas.filter { it.genero == Genero.FEMININO }.map { it.nome }
    return listaDeNomes
}

fun exercicio15(pessoas: List<Pessoa>): List<String> {
    //retornar List<String> com "Nome (GÊNERO) tem X anos e mora em Cidade"
    val nomeGerenoCidade = pessoas
        .map { "${it.nome} (${it.genero}) tem ${it.idade} anos e mora em ${it.cidade}" }
    return nomeGerenoCidade
}

fun exercicio16(pessoas: List<Pessoa>): List<Pessoa> {
    //retornar List<Pessoa> ordenada por genero (enum) e depois por nome
    return pessoas.
    sortedWith(compareBy({ it.genero }, { it.nome }))
}

fun exercicio17(pessoas: List<Pessoa>): List<Pessoa> {
    //retornar List<Pessoa> ordenada por cidade e, dentro, por idade decrescente
    val ordenarCidadeeIdade = pessoas
        .sortedWith(compareBy<Pessoa>({ it.cidade })
            .thenByDescending { it.idade })
    return ordenarCidadeeIdade
}

fun exercicio18(pessoas: List<Pessoa>): Int {
    // retornar Int com a soma das idades do gênero MASCULINO
    val somaIdadeMasculino = pessoas.filter { it.genero == Genero.MASCULINO }.sumOf { it.idade }
    return somaIdadeMasculino
}

fun exercicio19(pessoas: List<Pessoa>): Map<Genero, Double> {
    // retornar Map<Genero, Double> com idade média por gênero
    val retornaMediaGenero = pessoas
        .map({ it.genero to it.idade.toDouble() })
        .groupBy({ it.first }, { it.second })
    return retornaMediaGenero.mapValues { it.value.average() }
}

fun exercicio20(pessoas: List<Pessoa>): Pessoa? {
    // retornar Pessoa mais velha do gênero FEMININO (ou null se não houver)
    val maisVelha = pessoas
        .filter { it.genero == Genero.FEMININO }
        .maxByOrNull { it.idade }
    return maisVelha
}

fun exercicio21(pessoas: List<Pessoa>): Pessoa? {
    // retornar Pessoa mais nova do gênero INDEFINIDO (ou null se não houver)
    val pessoaMaisNova = pessoas
    .filter { it.genero == Genero.INDEFINIDO }
        .minByOrNull { it.idade }
    return pessoaMaisNova
}

fun exercicio22(pessoas: List<Pessoa>): List<String> {
    // COMPOSIÇÃO:
    // 1) filtrar pessoas de "São Paulo"
    // 2) transformar em List<String> de nomes
    // 3) ordenar alfabeticamente
    // retornar List<String>
    val ordernarNomesSP = pessoas
        .filter {"São Paulo" == it.cidade}
        .map { it.nome }
        .sorted()
    return ordernarNomesSP
}

fun exercicio23(pessoas: List<Pessoa>): Map<String, Int> {
    // COMPOSIÇÃO:
    // Para cada cidade, QUANTAS pessoas do gênero FEMININO estão empregadas
    //  retornar Map<String, Int>
    val mulheresEmpregadas = pessoas
        .filter { it.genero == Genero.FEMININO }
        .groupBy { it.cidade}
        .mapValues {it.value.count { p -> p.empregado }}
    return mulheresEmpregadas
}

fun exercicio24(pessoas: List<Pessoa>): Map<Genero, List<String>> {
    // COMPOSIÇÃO:
    // Mapa: genero -> lista de nomes ordenados por idade crescente
    //  retornar Map<Genero, List<String>>
    val listaOrdenadoIdadesCrescente = pessoas
    .sortedWith(compareBy<Pessoa>({ it.genero }, { it.idade }))
    .groupBy({ it.genero }, { it.nome })
    .mapValues { it.value.sorted() }
    return listaOrdenadoIdadesCrescente
}

fun exercicio25(pessoas: List<Pessoa>): Pessoa? {
    //  retornar a primeira Pessoa cuja cidade seja "Rio de Janeiro"
    val primeiraPessoaRJ = pessoas.firstOrNull { it.cidade == "Rio de Janeiro" }
    return primeiraPessoaRJ
}

fun exercicio26(pessoas: List<Pessoa>): Pessoa? {
    // TODO: retornar a última Pessoa cuja cidade seja "São Paulo"
    val ultimaPessoaSp = pessoas.lastOrNull{ it.cidade == "São Paulo" }
    return ultimaPessoaSp
}

fun exercicio27(pessoas: List<Pessoa>): Pessoa? {
    // TODO: retornar a primeira Pessoa que esteja desempregada (empregado == false)
    val primeiraPeDesempregada = pessoas.firstOrNull { !it.empregado }
    return primeiraPeDesempregada
}

fun exercicio28(pessoas: List<Pessoa>): Pessoa? {
    // TODO: retornar a última Pessoa do gênero INDEFINIDO
    val ultimaPessoaIndefinida = pessoas.lastOrNull { it.genero == Genero.INDEFINIDO }
    return ultimaPessoaIndefinida
}

fun exercicio29(pessoas: List<Pessoa>): Pessoa? {
    // TODO: retornar a primeira Pessoa com idade > 40
    val pessoaMaior40 = pessoas.firstOrNull { it.idade > 40 }
    return pessoaMaior40
}

fun exercicio30(pessoas: List<Pessoa>): Pessoa? {
    // TODO: retornar a última Pessoa com idade < 25
    val pessoaMenor25 = pessoas.lastOrNull { it.idade < 25 }
    return pessoaMenor25
}