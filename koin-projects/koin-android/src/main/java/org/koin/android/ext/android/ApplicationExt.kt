/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.koin.android.ext.android

import android.app.Application
import org.koin.android.ext.koin.bindAndroidProperties
import org.koin.android.ext.koin.with
import org.koin.android.logger.AndroidLogger
import org.koin.core.Koin
import org.koin.dsl.module.Module
import org.koin.log.Logger
import org.koin.standalone.StandAloneContext

/**
 * Application Android extensions
 *
 * @author Arnaud Giuliani
 */

/**
 * Create a new Koin ModuleDefinition
 * @param application - Android application
 * @param modules - list of AndroidModule
 * @param extraProperties - extra extraProperties
 * @param loadProperties - laod extraProperties from asset/koin.extraProperties
 * @param logger - default Koin logger
 * @param createOnStart - create flagged instances on start
 *
 * will be soon deprecated for starKoin() with <application>
 */
fun Application.startKoin(
    application: Application,
    modules: List<Module>,
    extraProperties: Map<String, Any> = HashMap(),
    loadProperties: Boolean = true,
    logger: Logger = AndroidLogger(),
    createOnStart: Boolean = true
) {
    Koin.logger = logger

    val koin = StandAloneContext.startKoin(
        modules,
        false,
        false,
        extraProperties,
        logger,
        false
    ).with(application)

    if (createOnStart) {
        StandAloneContext.createEagerInstances()
    }

    if (loadProperties) {
        koin.bindAndroidProperties(application)
    }
}

/**
 * Create a new Koin ModuleDefinition
 * @param modules - list of AndroidModule
 * @param extraProperties - extra extraProperties
 * @param loadProperties - laod extraProperties from asset/koin.extraProperties
 * @param logger - default Koin logger
 *
 * will be soon deprecated for starKoin() with <application>
 */
fun Application.startKoin(
    modules: List<Module>,
    extraProperties: Map<String, Any> = HashMap(),
    loadProperties: Boolean = true,
    logger: Logger = AndroidLogger(),
    createOnStart: Boolean = true
) {
    startKoin(this, modules, extraProperties, loadProperties, logger, createOnStart)
}