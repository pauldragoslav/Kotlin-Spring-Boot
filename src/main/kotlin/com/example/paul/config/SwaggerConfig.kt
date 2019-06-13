package com.example.paul.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Autowired
    lateinit var buildProperties: Optional<BuildProperties>
    @Autowired
    lateinit var gitProperties: Optional<GitProperties>

    @Bean
    fun api(): Docket {
        var version = "1.0"
        if (buildProperties.isPresent && gitProperties.isPresent) {
            val buildInfo = buildProperties.get()
            val gitInfo = gitProperties.get()
            version = "${buildInfo.version}-${gitInfo.shortCommitId}-${gitInfo.branch}"
        }

        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(version))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths { it.equals("/persons") }
                .build()
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
    }

    private fun apiInfo(version: String): ApiInfo {
        return ApiInfoBuilder()
                .title("API - Person Service")
                .description("Persons Management")
                .version(version)
                .build()
    }

    @Bean
    fun uiConfig(): UiConfiguration {
        return UiConfiguration(
                true,
                false,
                1,
                1,
                ModelRendering.MODEL,
                false,
                DocExpansion.LIST,
                false,
                null,
                OperationsSorter.ALPHA,
                false,
                TagsSorter.ALPHA,
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                null)
    }
}
