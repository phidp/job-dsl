import groovy.io.FileType

def workDir = SEED_JOB.getWorkspace()

def dir = new File("${workDir}/job-dsl")
def rootPath = dir.path

dir.eachFileRecurse (FileType.DIRECTORIES) { fol ->

    def relativePath = fol.path.replace(rootPath + '/', '')
    def formatedPath = relativePath.toLowerCase().replaceAll(' ', '-')
    println("Creating folder: ${relativePath}")
    println("Creating folder: ${formatedPath}")

    folder("${formatedPath}") {
        displayName(fol.name)
        description(fol.name)
    }
}