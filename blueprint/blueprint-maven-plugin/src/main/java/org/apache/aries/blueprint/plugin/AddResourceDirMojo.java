/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.aries.blueprint.plugin;

import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 * Creates resource base dir where blueprint file will be generated for IDE support
 */
@Mojo(name="add-resource-dir",
    requiresDependencyResolution= ResolutionScope.COMPILE,
    defaultPhase= LifecyclePhase.GENERATE_RESOURCES,
    inheritByDefault=false, threadSafe = true)
public class AddResourceDirMojo extends AbstractMojo {

    @Parameter(defaultValue="${project}", required=true)
    protected MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String buildDir = project.getBuild().getDirectory();
        String generatedBaseDir = buildDir + "/generated-sources/blueprint";
        Resource resource = new Resource();
        resource.setDirectory(generatedBaseDir);
        project.addResource(resource);
    }
}
