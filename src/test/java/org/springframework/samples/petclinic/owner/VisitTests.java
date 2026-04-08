/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Visit} domain class.
 */
class VisitTests {

	@Test
	void constructorSetsDateToToday() {
		Visit visit = new Visit();
		assertThat(visit.getDate()).isEqualTo(LocalDate.now());
	}

	@Test
	void setDateAndGetDate() {
		Visit visit = new Visit();
		LocalDate date = LocalDate.of(2023, 5, 10);
		visit.setDate(date);
		assertThat(visit.getDate()).isEqualTo(date);
	}

	@Test
	void setDescriptionAndGetDescription() {
		Visit visit = new Visit();
		visit.setDescription("Routine check-up");
		assertThat(visit.getDescription()).isEqualTo("Routine check-up");
	}

	@Test
	void descriptionIsNullByDefault() {
		Visit visit = new Visit();
		assertThat(visit.getDescription()).isNull();
	}

	@Test
	void isNewWhenIdNotSet() {
		Visit visit = new Visit();
		assertThat(visit.isNew()).isTrue();
	}

	@Test
	void isNotNewAfterIdSet() {
		Visit visit = new Visit();
		visit.setId(10);
		assertThat(visit.isNew()).isFalse();
	}

}
