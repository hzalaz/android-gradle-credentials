/*
 * CredentialsExtension.groovy
 *
 * Copyright (c) 2016 Auth0 (http://auth0.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.auth0.android.gradle.credentials.extensions

import com.android.annotations.NonNull
import org.gradle.api.plugins.ExtensionAware

import static com.auth0.android.gradle.credentials.Utils.isValidString

@SuppressWarnings("GroovyUnusedDeclaration")
public class CredentialsExtension implements ExtensionValidator {

    private static final String RES_CLIENT_ID_KEY = "com_auth0_client_id"
    private static final String RES_DOMAIN_KEY = "com_auth0_domain"

    String domain
    String clientId

    //Extensions
    GuardianExtension guardian

    public CredentialsExtension() {
        guardian = ((ExtensionAware) this).getExtensions().create("guardian", GuardianExtension.class)
    }

    @Override
    boolean hasRequiredProperties() {
        return isValidString(domain) && isValidString(clientId)
    }

    @Override
    @NonNull
    Map getValues() {
        Map values = new HashMap<>()
        values.put(RES_DOMAIN_KEY, domain)
        values.put(RES_CLIENT_ID_KEY, clientId)

        //Extensions
        values.putAll(guardian.getValues())
        return values
    }
}