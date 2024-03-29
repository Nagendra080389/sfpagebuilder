/** ========================================================================= *
 * Copyright (C)  2017, 2018 Salesforce Inc ( http://www.salesforce.com/      *
 *                            All rights reserved.                            *
 *                                                                            *
 *  @author     Stephan H. Wissel (stw) <swissel@salesforce.com>              *
 *                                       @notessensei                         *
 * @version     1.0                                                           *
 * ========================================================================== *
 *                                                                            *
 * Licensed under the  Apache License, Version 2.0  (the "License").  You may *
 * not use this file except in compliance with the License.  You may obtain a *
 * copy of the License at <http://www.apache.org/licenses/LICENSE-2.0>.       *
 *                                                                            *
 * Unless  required  by applicable  law or  agreed  to  in writing,  software *
 * distributed under the License is distributed on an  "AS IS" BASIS, WITHOUT *
 * WARRANTIES OR  CONDITIONS OF ANY KIND, either express or implied.  See the *
 * License for the  specific language  governing permissions  and limitations *
 * under the License.                                                         *
 *                                                                            *
 * ========================================================================== *
 */
package com.notessensei.heroku.springcanvassample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.samskivert.mustache.Mustache;

@SpringBootApplication
/* Important: Don't add @EnableWebMvc here, screws things up. Badly! */
public class SfpagebuilderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SfpagebuilderApplication.class, args);
    }

    /**
     * Ensure that the template doesn't blow up when invalid parameters are
     * specified
     *
     * @param templateLoader
     *            template source
     * @param environment
     *            runtime environment
     * @return a Mustache compiler
     */
    @Bean
    public Mustache.Compiler mustacheCompiler(final Mustache.TemplateLoader templateLoader,
            final Environment environment) {

        final MustacheEnvironmentCollector collector = new MustacheEnvironmentCollector();
        collector.setEnvironment(environment);

        return Mustache.compiler()
                .defaultValue(":-(")
                .withLoader(templateLoader)
                .withCollector(collector);

    }
}
