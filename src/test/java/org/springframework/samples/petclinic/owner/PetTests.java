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
 * Unit tests for the {@link Pet} domain class.
 */
class PetTests {

	@Test
	void setBirthDateAndGetBirthDate() {
		Pet pet = new Pet();
		LocalDate birthDate = LocalDate.of(2020, 6, 15);
		pet.setBirthDate(birthDate);
		assertThat(pet.getBirthDate()).isEqualTo(birthDate);
	}

	@Test
	void birthDateIsNullByDefault() {
		Pet pet = new Pet();
		assertThat(pet.getBirthDate()).isNull();
	}

	@Test
	void setTypeAndGetType() {
		Pet pet = new Pet();
		PetType type = new PetType();
		type.setName("cat");
		pet.setType(type);
		assertThat(pet.getType()).isSameAs(type);
	}

	@Test
	void typeIsNullByDefault() {
		Pet pet = new Pet();
		assertThat(pet.getType()).isNull();
	}

	@Test
	void addVisitAppendsToVisits() {
		Pet pet = new Pet();
		Visit visit = new Visit();
		visit.setDescription("Annual check-up");
		pet.addVisit(visit);
		assertThat(pet.getVisits()).containsExactly(visit);
	}

	@Test
	void getVisitsIsEmptyByDefault() {
		Pet pet = new Pet();
		assertThat(pet.getVisits()).isEmpty();
	}

	@Test
	void isNewWhenIdNotSet() {
		Pet pet = new Pet();
		assertThat(pet.isNew()).isTrue();
	}

	@Test
	void isNotNewAfterIdSet() {
		Pet pet = new Pet();
		pet.setId(7);
		assertThat(pet.isNew()).isFalse();
	}

}
