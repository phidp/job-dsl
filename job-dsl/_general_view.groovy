import groovy.io.FileType

def workDir = SEED_JOB.getWorkspace()

def dir = new File("${workDir}/job-dsl")
def rootPath = dir.path

dir.eachFileRecurse (FileType.DIRECTORIES) { fol ->
    def relativePath = fol.path.replace(rootPath + '/', '')
    def formatedPath = relativePath.toLowerCase().replaceAll(' ', '-').replaceAll('_', '-')
    def formatedName = formatInput(fol.name)
    println("Creating folder: ${relativePath}")
    println("Creating folder: ${formatedPath}")

    folder("${formatedPath}") {
        displayName(fol.name)
        description(fol.name)
    }
}

def formatInput(String input) {
    // Replace underscores with spaces
    String replaced = input.replace('_', ' ')

    // Split the string into words
    List<String> words = replaced.split(' ')

    // Capitalize each word
    List<String> capitalizedWords = words.collect { it.capitalize() }

    // Join words back together
    String formattedInput = capitalizedWords.join(' ')

    return formattedInput
}