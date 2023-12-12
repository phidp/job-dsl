import groovy.io.FileType
def workDir = SEED_JOB.getWorkspace()
println(workDir)

def dir = new File('/Users/phidp/.jenkins/workspace/test_jobDsl/job-dsl')
dir.eachFileRecurse (FileType.DIRECTORIES) { fol ->
    // def relativePath = folder.path - "${dir.path}/"
    // def folderPath = relativePath.replaceAll("/", "/job/").replaceAll(' ', '-')
    
	println(dir.name)
	println(fol.name)
	println(fol.path)
    println("Creating folder: ${fol.name}")
    
    folder("${fol.name}") {
		displayName(fol.name)
		description(fol.name)
	}
}