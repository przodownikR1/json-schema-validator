/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.eel.kitchen.jsonschema.keyword.common;

import com.fasterxml.jackson.databind.JsonNode;
import org.eel.kitchen.jsonschema.keyword.KeywordValidator;
import org.eel.kitchen.jsonschema.main.JsonValidationFailureException;
import org.eel.kitchen.jsonschema.main.ValidationContext;
import org.eel.kitchen.jsonschema.main.ValidationReport;

/**
 * Keyword validation for the {@code maxItems} keyword (draft section 5.14)
 */
public final class MaxItemsKeywordValidator
    extends KeywordValidator
{
    private static final MaxItemsKeywordValidator instance
        = new MaxItemsKeywordValidator();

    private MaxItemsKeywordValidator()
    {
        super("maxItems");
    }

    public static MaxItemsKeywordValidator getInstance()
    {
        return instance;
    }

    @Override
    public ValidationReport validate(final ValidationContext context,
        final JsonNode instance)
        throws JsonValidationFailureException
    {
        final ValidationReport report = context.createReport();
        final int maxItems = context.getSchema().get(keyword).intValue();

        if (instance.size() > maxItems)
            report.message("array has more than maxItems elements");

        return report;
    }
}
