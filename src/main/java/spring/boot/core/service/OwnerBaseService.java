package spring.boot.core.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spring.boot.core.dao.model.BaseEntity;
import spring.boot.core.dao.model.OwnerBaseEntity;
import spring.boot.core.dao.repository.BaseRepository;
import spring.boot.core.dao.repository.OwnerBaseRepository;
import spring.boot.core.dto.BaseDTO;
import spring.boot.core.exception.BaseException;

import java.util.Objects;

public class OwnerBaseService<
        Entity extends OwnerBaseEntity,
        DTO extends BaseDTO,
        Repository extends OwnerBaseRepository<Entity,DTO,Long>>
        extends AbstractBaseService<Entity, DTO, Repository>{

    @Override
    protected Repository getRepository() {
        return null;
    }

    @Override
    @Cacheable
    public Page<DTO> search(DTO dto, Pageable pageable) {
        dto.setCreatedBy(getCurrentUserId());
        return getRepository().search(dto,pageable).map(this::mapToDTO);
    }

    @Override
    public DTO mapToDTO(Entity entity) {
        if(!entity.ignoreOwner && entity.getId() != null && !Objects.equals(entity.getCreatedBy(), getCurrentUserId())){
            throw new BaseException(1000, "Dữ liệu không thuộc về bạn");
        }
        return super.mapToDTO(entity);
    }

    @Override
    protected DTO save(Entity entity, DTO dto) {
        if(entity.getId() != null && !Objects.equals(entity.getCreatedBy(), getCurrentUserId())){
            throw new BaseException(1000, "Dữ liệu không thuộc về bạn");
        }
        return super.save(entity, dto);
    }

    @Override
    public void delete(Long id) {
        if(!getRepository().ownerOfId(id, getCurrentUserId())){
            throw new BaseException(1000, "Dữ liệu không thuộc về bạn");
        }
        super.delete(id);
    }
}
