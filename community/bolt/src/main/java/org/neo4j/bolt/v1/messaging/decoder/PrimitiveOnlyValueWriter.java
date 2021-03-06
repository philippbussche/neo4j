/*
 * Copyright (c) 2002-2018 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.bolt.v1.messaging.decoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.spatial.Point;
import org.neo4j.kernel.impl.util.BaseToObjectValueWriter;
import org.neo4j.values.AnyValue;
import org.neo4j.values.AnyValueWriter;
import org.neo4j.values.storable.CoordinateReferenceSystem;

/**
 * {@link AnyValueWriter Writer} that allows to convert {@link AnyValue} to any primitive Java type. It explicitly
 * prohibits conversion of nodes, relationships, spatial and temporal types. They are not expected in auth token map.
 */
class PrimitiveOnlyValueWriter extends BaseToObjectValueWriter<RuntimeException>
{
    Object valueAsObject( AnyValue value )
    {
        value.writeTo( this );
        return value();
    }

    @Override
    protected Node newNodeProxyById( long id )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain nodes" );
    }

    @Override
    protected Relationship newRelationshipProxyById( long id )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain relationships" );
    }

    @Override
    protected Point newPoint( CoordinateReferenceSystem crs, double[] coordinate )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain points" );
    }

    @Override
    public void writeByteArray( byte[] value )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain byte arrays" );
    }

    @Override
    public void writeDuration( long months, long days, long seconds, int nanos )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain durations" );
    }

    @Override
    public void writeDate( LocalDate localDate )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain dates" );
    }

    @Override
    public void writeLocalTime( LocalTime localTime )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain local dates" );
    }

    @Override
    public void writeTime( OffsetTime offsetTime )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain time values" );
    }

    @Override
    public void writeLocalDateTime( LocalDateTime localDateTime )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain local date-time values" );
    }

    @Override
    public void writeDateTime( ZonedDateTime zonedDateTime )
    {
        throw new UnsupportedOperationException( "INIT message metadata should not contain date-time values" );
    }
}
