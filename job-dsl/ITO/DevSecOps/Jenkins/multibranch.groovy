def function     = "ito"
def sub_function = "devsecops"
def system       = 'jenkins'
def credential   = 'dso-rw'
def repositories   = [
    [
        branches: "((master)|(develop.*)|(release\\/.*)|(MR-.*)|(.*-uat-.*)|(\\d+\\.\\d+\\.\\d+))",
        owner: "ito/devsecops/jenkins",
        projects: [
            'pipeline-1'
        ],
        branches: "((master)|(develop.*)|(release\\/.*)|(MR-.*)|(.*-uat-.*)|(\\d+\\.\\d+\\.\\d+))",
        owner: "ito/devsecops/gitlab",
        projects: [
            'pipeline-1',
            'project2'
        ]
    ]
]

repositories.each { repo ->
    repo.projects.each { project ->
        pipelinePath = "${function}/${sub_function}/${system}/${project}"
        displayName = project.split('-').collect { it.capitalize() }.join(' ')
        multibranchPipelineJob("${pipelinePath}") {
            branchSources {
                displayName("${displayName}")
                branchSource {
                    source {
                        gitLabSCMSource {
                            serverName("Gitlab")
                            credentialsId("${credential}")
                            projectOwner("${repo.owner}")
                            projectPath("${repo.owner}/${project}")
                            traits {
                                gitLabBranchDiscovery {
                                    strategyId(3)
                                }
                                originMergeRequestDiscoveryTrait{
                                    strategyId(1)
                                }
                                gitLabTagDiscovery()
                                headRegexFilter {
                                    regex(repo.branches)
                                }
                            }
                        }
                    }
                    // buildStrategies {
                    //     skipInitialBuildOnFirstBranchIndexing()
                    //     buildChangeRequests {
                    //         ignoreTargetOnlyChanges(false)
                    //         ignoreUntrustedChanges(false)
                    //     }
                    //     buildTags {
                    //         atLeastDays("")
                    //         atMostDays("7")
                    //     }
                    // } 
                }
            }
        }
    }
}