package com.example.clothesstoreapp.datasource.utils

interface EntityMapper <Entity, DomainModel>{

    fun mapFromLocalEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

}