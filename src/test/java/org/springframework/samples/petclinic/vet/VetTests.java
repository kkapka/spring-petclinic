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
package org.springframework.samples.petclinic.vet;

import org.junit.jupiter.api.Test;
import org.springframework.util.SerializationUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dave Syer
 */
class VetTests {

	@Test
	void serialization() {
		Vet vet = new Vet();
		vet.setFirstName("Zaphod");
		vet.setLastName("Beeblebrox");
		vet.setId(123);
		@SuppressWarnings("deprecation")
		Vet other = (Vet) SerializationUtils.deserialize(SerializationUtils.serialize(vet));
		assertThat(other.getFirstName()).isEqualTo(vet.getFirstName());
		assertThat(other.getLastName()).isEqualTo(vet.getLastName());
		assertThat(other.getId()).isEqualTo(vet.getId());
	}

	@Test
	void newVetHasNoSpecialties() {
		Vet vet = new Vet();
		assertThat(vet.getNrOfSpecialties()).isEqualTo(0);
		assertThat(vet.getSpecialties()).isEmpty();
	}

	@Test
	void addSpecialtyIncreasesCount() {
		Vet vet = new Vet();
		Specialty radiology = new Specialty();
		radiology.setName("radiology");
		vet.addSpecialty(radiology);
		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
	}

	@Test
	void getSpecialtiesReturnsSortedByName() {
		Vet vet = new Vet();
		Specialty surgery = new Specialty();
		surgery.setName("surgery");
		Specialty dentistry = new Specialty();
		dentistry.setName("dentistry");
		Specialty radiology = new Specialty();
		radiology.setName("radiology");
		vet.addSpecialty(surgery);
		vet.addSpecialty(dentistry);
		vet.addSpecialty(radiology);
		assertThat(vet.getSpecialties()).extracting(Specialty::getName)
			.containsExactly("dentistry", "radiology", "surgery");
	}

	@Test
	void addDuplicateSpecialtyDoesNotIncreaseCount() {
		Vet vet = new Vet();
		Specialty radiology = new Specialty();
		radiology.setName("radiology");
		vet.addSpecialty(radiology);
		vet.addSpecialty(radiology);
		assertThat(vet.getNrOfSpecialties()).isEqualTo(1);
	}

}
