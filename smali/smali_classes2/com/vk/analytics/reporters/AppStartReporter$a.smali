.class public final Lcom/vk/analytics/reporters/AppStartReporter$a;
.super Ljava/lang/Object;
.source "AppStartReporter.kt"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/vk/analytics/reporters/AppStartReporter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "a"
.end annotation


# instance fields
.field private final a:Ljava/lang/String;

.field private final b:Landroid/location/Location;


# direct methods
.method public constructor <init>(Ljava/lang/String;Landroid/location/Location;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->a:Ljava/lang/String;

    iput-object p2, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->b:Landroid/location/Location;

    return-void
.end method

.method public synthetic constructor <init>(Ljava/lang/String;Landroid/location/Location;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    .locals 0

    and-int/lit8 p3, p3, 0x2

    if-eqz p3, :cond_0

    const/4 p2, 0x0

    .line 2
    :cond_0
    invoke-direct {p0, p1, p2}, Lcom/vk/analytics/reporters/AppStartReporter$a;-><init>(Ljava/lang/String;Landroid/location/Location;)V

    return-void
.end method


# virtual methods
.method public final a()Landroid/location/Location;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->b:Landroid/location/Location;

    return-object v0
.end method

.method public final b()Ljava/lang/String;
    .locals 1

    .line 1
    iget-object v0, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->a:Ljava/lang/String;

    return-object v0
.end method

.method public equals(Ljava/lang/Object;)Z
    .locals 2

    if-eq p0, p1, :cond_1

    instance-of v0, p1, Lcom/vk/analytics/reporters/AppStartReporter$a;

    if-eqz v0, :cond_0

    check-cast p1, Lcom/vk/analytics/reporters/AppStartReporter$a;

    iget-object v0, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->a:Ljava/lang/String;

    iget-object v1, p1, Lcom/vk/analytics/reporters/AppStartReporter$a;->a:Ljava/lang/String;

    invoke-static {v0, v1}, Lkotlin/jvm/internal/Intrinsics;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->b:Landroid/location/Location;

    iget-object p1, p1, Lcom/vk/analytics/reporters/AppStartReporter$a;->b:Landroid/location/Location;

    invoke-static {v0, p1}, Lkotlin/jvm/internal/Intrinsics;->a(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result p1

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 p1, 0x0

    return p1

    :cond_1
    :goto_0
    const/4 p1, 0x1

    return p1
.end method

.method public hashCode()I
    .locals 3

    iget-object v0, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->a:Ljava/lang/String;

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/lang/Object;->hashCode()I

    move-result v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    mul-int/lit8 v0, v0, 0x1f

    iget-object v2, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->b:Landroid/location/Location;

    if-eqz v2, :cond_1

    invoke-virtual {v2}, Ljava/lang/Object;->hashCode()I

    move-result v1

    :cond_1
    add-int/2addr v0, v1

    return v0
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "GeoData(state="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->a:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", location="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v1, p0, Lcom/vk/analytics/reporters/AppStartReporter$a;->b:Landroid/location/Location;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ")"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method