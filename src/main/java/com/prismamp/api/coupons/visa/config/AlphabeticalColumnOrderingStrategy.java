package com.prismamp.api.coupons.visa.config;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.model.relational.ColumnOrderingStrategyStandard;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Table;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import com.prismamp.api.coupons.visa.model.VisaCoupon;

@Configuration
public class AlphabeticalColumnOrderingStrategy extends ColumnOrderingStrategyStandard
		implements HibernatePropertiesCustomizer {
	
	private static Logger LOGGER= LogManager.getLogger(AlphabeticalColumnOrderingStrategy.class);

	@Override
	public List<Column> orderTableColumns(Table table, Metadata metadata) {

		if (table.getNameIdentifier().getText().equals("tx_visa")) {
			List<Column> ordenedColumns = new ArrayList<>();

			Map<String, Column> columns = table.getColumns().stream()
					.collect(Collectors.toMap(c -> c.getName(), c -> c));

			List<Field> allFields = Arrays.asList(VisaCoupon.class.getDeclaredFields());

			for (Field field : allFields) {

				if (columns.containsKey(field.getName())) {
					ordenedColumns.add(columns.get(field.getName()));
					
					LOGGER.info("DB ORDER -> " + field.getName());
				} else {
					LOGGER.error("ERROR -> Field not found - " + field.getName());
				}
			}

			return ordenedColumns;

		} else
			return null;
	}

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put(AvailableSettings.COLUMN_ORDERING_STRATEGY, this);
	}
}
