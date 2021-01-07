package moriyashiine.bewitchment.api.interfaces;

import java.util.Optional;
import java.util.UUID;

public interface PolymorphAccessor {
	static Optional<PolymorphAccessor> of(Object entity) {
		if (entity instanceof PolymorphAccessor) {
			return Optional.of(((PolymorphAccessor) entity));
		}
		return Optional.empty();
	}
	
	Optional<UUID> getPolymorphUUID();
	
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	void setPolymorphUUID(Optional<UUID> uuid);
	
	String getPolymorphName();
	
	void setPolymorphName(String name);
}
