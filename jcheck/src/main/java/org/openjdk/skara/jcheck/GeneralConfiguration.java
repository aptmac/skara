/*
 * Copyright (c) 2019, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.skara.jcheck;

import org.openjdk.skara.ini.Section;

import java.util.Optional;

public class GeneralConfiguration {
    private static final GeneralConfiguration DEFAULT = new GeneralConfiguration(null, null, null, null);

    private final String project;
    private final String repository;
    private final String jbs;
    private final String version;

    private GeneralConfiguration(String project, String repository, String jbs, String version) {
        this.project = project;
        this.repository = repository;
        this.jbs = jbs;
        this.version = version;
    }

    public String project() {
        return project;
    }

    public String repository() {
        return repository;
    }

    public String jbs() {
        return jbs;
    }

    public Optional<String> version() {
        return Optional.ofNullable(version);
    }

    static String name() {
        return "general";
    }

    static GeneralConfiguration parse(Section s) {
        if (s == null) {
            return DEFAULT;
        }

        var project = s.get("project", DEFAULT.project());
        var repository = s.get("repository", DEFAULT.repository());
        var jbs = s.get("jbs", DEFAULT.jbs());
        var version = s.get("version", DEFAULT.version().orElse(null));
        return new GeneralConfiguration(project, repository, jbs, version);
    }
}
