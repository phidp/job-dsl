// import groovy.io.FileType
// def workDir = SEED_JOB.getWorkspace()
// println(workDir)

// def dir = new File('/Users/phidp/.jenkins/workspace/test_jobDsl/job-dsl')
// dir.eachFileRecurse (FileType.DIRECTORIES) { fol ->
//     println("Creating folder: ${fol.name}")
    
//     folder("${fol.name}") {
// 		displayName(fol.name)
// 		description(fol.name)
// 	}
// }

import groovy.io.FileType

def dir = new File('/Users/phidp/.jenkins/workspace/test_jobDsl/job-dsl')
def rootPath = dir.path

dir.eachFileRecurse (FileType.DIRECTORIES) { fol ->
    def relativePath = fol.path.replace(rootPath + '/', '')
    println("Creating folder: ${relativePath}")
    
    folder("${relativePath}") {
        displayName(fol.name)
        description(fol.name)
    }
}