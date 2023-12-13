import groovy.io.FileType

def workDir = SEED_JOB.getWorkspace()

def dir = new File("${workDir}/job-dsl")

dir.eachFileRecurse (FileType.DIRECTORIES) { fol ->
    def path = formatInputPath(fol.path)
    def name = formatInputName(fol.name)

    folder(path) {
        displayName(name)
        description(name)
    }
}

def formatInputPath( String input) {
    def rootPath = dir.path
    String relativePath = input.replace(rootPath + '/', '')
    String formattedInput = relativePath.toLowerCase().replaceAll(' ', '-').replaceAll('_', '-')
    return formattedInput
}

def formatInputName(String input) {
    println("Folder name is: ${input}")
    // Replace underscores with spaces
    String replaced = input.replace('_', ' ')

    // Split the string into words
    List<String> words = replaced.split(' ')

    // Capitalize each word only if its first letter is not already capitalized
    List<String> formattedWords = words.collect { 
        it == it.toUpperCase() ? it : it.capitalize()
    }

    // Join words back together
    String formattedInput = formattedWords.join(' ')

    return formattedInput
}